package com.ebanx.swipebutton

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.matcher.ViewMatchers.withId
import ebanx.com.ego.utils.CenterSwipeAction

/**
 * Created by vinicius on 13/06/17.
 */
class SwipeButtonTestRobot {

    fun enableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(swipeRight())
        return this
    }

    fun disableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(click())
        return this
    }

    fun enableFromMidOfButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(swipeFromCenterToRight())
        return this
    }

    private fun swipeFromCenterToRight(): ViewAction {
        val endCoordProvide = CoordinatesProvider { view ->
            val coordinates = GeneralLocation.CENTER.calculateCoordinates(view)
            coordinates[0] = 1500f
            coordinates
        }
        return CenterSwipeAction(
            Swipe.FAST,
                GeneralLocation.CENTER,
                endCoordProvide,
                Press.FINGER)
    }

    infix fun result(func: SwipeButtonResultRobot.() -> Unit) = SwipeButtonResultRobot().apply { func() }
}