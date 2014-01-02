package cn.com.oll.life;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * һ������ֻ�ҡ�εļ�����
 */
public class ShakeListener implements SensorEventListener {
	// �ٶ���ֵ����ҡ���ٶȴﵽ��ֵ���������
	private static final int SPEED_SHRESHOLD = 3000;
	// ���μ���ʱ����
	private static final int UPTATE_INTERVAL_TIME = 70;
	// ������������
	private SensorManager sensorManager;
	// ������
	private Sensor sensor;
	// ������Ӧ������
	private OnShakeListener onShakeListener;
	// ������
	private Context mContext;
	// �ֻ���һ��λ��ʱ������Ӧ���
	private float lastX;
	private float lastY;
	private float lastZ;
	// �ϴμ��ʱ��
	private long lastUpdateTime;

	// ������
	public ShakeListener(Context c) {
		// ��ü������
		mContext = c;
		start();
	}

	// ��ʼ
	public void start() {
		// ��ô�����������
		sensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager != null) {
			// �������������
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		// ע��
		if (sensor != null) {
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_GAME);
		}

	}

	// ֹͣ���
	public void stop() {
		sensorManager.unregisterListener(this);
	}

	// ����������Ӧ������
	public void setOnShakeListener(OnShakeListener listener) {
		onShakeListener = listener;
	}

	// ������Ӧ����Ӧ��ñ仯���
	public void onSensorChanged(SensorEvent event) {
		// ���ڼ��ʱ��
		long currentUpdateTime = System.currentTimeMillis();
		// ���μ���ʱ����
		long timeInterval = currentUpdateTime - lastUpdateTime;
		// �ж��Ƿ�ﵽ�˼��ʱ����
		if (timeInterval < UPTATE_INTERVAL_TIME)
			return;
		// ���ڵ�ʱ����lastʱ��
		lastUpdateTime = currentUpdateTime;

		// ���x,y,z���
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		// ���x,y,z�ı仯ֵ
		float deltaX = x - lastX;
		float deltaY = y - lastY;
		float deltaZ = z - lastZ;

		// �����ڵ������last���
		lastX = x;
		lastY = y;
		lastZ = z;

		double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
				* deltaZ)
				/ timeInterval * 10000;
		Log.v("thelog", "===========log===================");
		// �ﵽ�ٶȷ�ֵ��������ʾ
		if (speed >= SPEED_SHRESHOLD) {
			onShakeListener.onShake();
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	// ҡ�μ���ӿ�
	public interface OnShakeListener {
		public void onShake();
	}

}