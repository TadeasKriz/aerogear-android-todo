/*
 * Copyright (c) RedHat, 2012.
 */

package org.aerogear.proto.todos.activities;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.aerogear.proto.todos.R;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void appNameTest() {
        String appName = new MainActivity().getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("TODOs"));
    }
}
