package io.github.trojan_gfw.igniter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRouter;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import io.github.trojan_gfw.igniter.common.constants.Constants;
import io.github.trojan_gfw.igniter.common.utils.PreferenceUtils;
import io.github.trojan_gfw.igniter.connection.TrojanConnection;
import io.github.trojan_gfw.igniter.proxy.aidl.ITrojanService;
import io.github.trojan_gfw.igniter.tile.ProxyHelper;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean allowAutoStart = PreferenceUtils.getBooleanPreference(context.getContentResolver(),
                Uri.parse(Constants.PREFERENCE_URI), Constants.PREFERENCE_KEY_ALLOW_AUTO_START,
                false);
        if (allowAutoStart){
            startProxyService(context);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startProxyService(Context context){
        if(ProxyHelper.isTrojanConfigValid() && ProxyHelper.isVPNServiceConsented(context)){
            ProxyHelper.startProxyService(context);
        }else{
            ProxyHelper.startLauncherActivity(context);
        }
    }

//    private void stopProxyService(Context context){
//        ProxyHelper.stopProxyService(context);
//    }
}