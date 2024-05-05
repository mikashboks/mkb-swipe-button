package ebanx.com.ego.utils

import android.view.View
import android.view.ViewConfiguration
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.PrecisionDescriber
import androidx.test.espresso.action.Swiper
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher

class CenterSwipeAction(private val swiper: Swiper,
                        private val startCoordProvide: CoordinatesProvider,
                        private val endCoordProvide: CoordinatesProvider,
                        private val precDesc: PrecisionDescriber
) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
    }

    override fun getDescription(): String {
        return "swipe from middle of screen"
    }

    @Suppress("CatchRuntimeException")
    override fun perform(uiController: UiController, view: View) {
        val startCoord = startCoordProvide.calculateCoordinates(view)
        val finalCoord = endCoordProvide.calculateCoordinates(view)
        val precision = precDesc.describePrecision()

        // you could try this for several times until Swiper.Status is achieved or try count is reached
        try {
            swiper.sendSwipe(uiController, startCoord, finalCoord, precision)
        } catch (re: RuntimeException) {
            throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(re)
                    .build()
        }

        // ensures that the swipe has been run.
        uiController.loopMainThreadForAtLeast(ViewConfiguration.getPressedStateDuration().toLong())
    }
}
