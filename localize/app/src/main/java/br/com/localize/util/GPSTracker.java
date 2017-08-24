package br.com.localize.util;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {

	private final Context mContext;

	boolean isGPSEnabled = false;

	boolean isNetworkEnabled = false;

	boolean canGetLocation = false;

	Location location;

	double latitude;

	double longitude;

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

	protected LocationManager locationManager;

	public GPSTracker( final Context context ) {

		this.mContext = context;

		this.getLocation();
	}

	public Location getLocation() {

		try {
			this.locationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);

			this.isGPSEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

			this.isNetworkEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!this.isGPSEnabled && !this.isNetworkEnabled) {
			} else {
				this.canGetLocation = true;

				if (this.isNetworkEnabled) {

					this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, GPSTracker.MIN_TIME_BW_UPDATES, GPSTracker.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

					Log.d("Network", "Network");

					if (this.locationManager != null) {

						this.location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

						if (this.location != null) {

							this.latitude = this.location.getLatitude();

							this.longitude = this.location.getLongitude();
						}
					}
				}

				if (this.isGPSEnabled) {

					if (this.location == null) {

						this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPSTracker.MIN_TIME_BW_UPDATES, GPSTracker.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

						Log.d("GPS Enabled", "GPS Enabled");

						if (this.locationManager != null) {

							this.location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

							if (this.location != null) {

								this.latitude = this.location.getLatitude();

								this.longitude = this.location.getLongitude();
							}
						}
					}
				}
			}

		} catch (final Exception e) {

			e.printStackTrace();

		}

		return this.location;
	}

	public void stopUsingGPS() {

		if (this.locationManager != null) {
			this.locationManager.removeUpdates(GPSTracker.this);
		}
	}

	public double getLatitude() {

		if (this.location != null) {

			this.latitude = this.location.getLatitude();
		}

		return this.latitude;
	}

	public double getLongitude() {

		if (this.location != null) {

			this.longitude = this.location.getLongitude();

		}

		return this.longitude;
	}

	public boolean canGetLocation() {

		return this.canGetLocation;
	}

	public void showSettingsAlert() {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.mContext);

		alertDialog.setTitle("Configurar GPS");

		alertDialog.setMessage("GPS não está ativo, deseja ativa-lo nas configurações ?");

		alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {

				final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

				GPSTracker.this.mContext.startActivity(intent);

			}

		});

		alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {

				dialog.cancel();
			}
		});

		alertDialog.show();
	}

	@Override
	public void onLocationChanged(final Location location) {

	}

	@Override
	public void onProviderDisabled(final String provider) {

	}

	@Override
	public void onProviderEnabled(final String provider) {

	}

	@Override
	public void onStatusChanged(final String provider, final int status, final Bundle extras) {

	}

	@Override
	public IBinder onBind(final Intent arg0) {

		return null;
	}

}
