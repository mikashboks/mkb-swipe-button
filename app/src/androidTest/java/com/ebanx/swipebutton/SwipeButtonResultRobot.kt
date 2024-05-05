package com.ebanx.swipebutton

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.ebanx.swipebtn.SwipeButton
import junit.framework.Assert
import org.hamcrest.Matchers.allOf

/**
 * Created by vinicius on 13/06/17.
 */
class SwipeButtonResultRobot {

    fun checkButtonIsEnabled(swipeButton: SwipeButton) {
        Assert.assertTrue(swipeButton.isActive)
    }

    fun checkButtonIsDisabled(swipeButton: SwipeButton) {
        Assert.assertFalse(swipeButton.isActive)
    }

    fun shouldBeginWithRightText(title: String) {
        onView(withText(title)).check(matches(isDisplayed()))
    }

}