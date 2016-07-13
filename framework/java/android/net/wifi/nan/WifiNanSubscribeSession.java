/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.net.wifi.nan;

import android.annotation.NonNull;
import android.util.Log;

/**
 * A representation of a NAN subscribe session. Created when
 * {@link WifiNanManager#subscribe(SubscribeConfig, WifiNanSessionCallback)} is
 * executed. The object can be used to stop and re-start (re-configure) the
 * subscribe session.
 *
 * @hide PROPOSED_NAN_API
 */
public class WifiNanSubscribeSession extends WifiNanSession {
    private static final String TAG = "WifiNanSubscribeSession";

    /**
     * {@hide}
     */
    public WifiNanSubscribeSession(WifiNanManager manager, int sessionId) {
        super(manager, sessionId);
    }

    /**
     * Re-configure the subscribe session. Note that the
     * {@link WifiNanSessionCallback} is not replaced - the same listener used
     * at creation is still used.
     *
     * @param subscribeConfig The configuration ({@link SubscribeConfig}) of the
     *            subscribe session.
     */
    public void updateSubscribe(@NonNull SubscribeConfig subscribeConfig) {
        if (mTerminated) {
            Log.w(TAG, "updateSubscribe: called on terminated session");
            return;
        } else {
            WifiNanManager mgr = mMgr.get();
            if (mgr == null) {
                Log.w(TAG, "updateSubscribe: called post GC on WifiNanManager");
                return;
            }

            mgr.updateSubscribe(mSessionId, subscribeConfig);
        }
    }
}