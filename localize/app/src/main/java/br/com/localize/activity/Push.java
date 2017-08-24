package br.com.localize.activity;

import android.app.Application;

import com.parse.Parse;
import com.parse.PushService;

public class Push extends Application {

	@Override
	public void onCreate() {

        Parse.initialize(this, "UzI7yd2TnKvS0xCM75OIFfGXtmtIG8dL9hF6j7Yx", "ZfHesV7skoEy0D9smbzb4NTDYpQC6OR5hJTaZAEh");

        PushService.setDefaultPushCallback(this, MainActivity.class);

    }


}
