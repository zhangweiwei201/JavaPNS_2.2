package javapns;

import java.util.List;

import javapns.devices.Device;
import javapns.notification.PushNotificationPayload;

public class PushSendDto
{
	public List<Device> deviceList = null;

	public PushNotificationPayload payLoad = null;

}
