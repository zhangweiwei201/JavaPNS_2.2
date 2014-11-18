package javapns;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

public class Test
{

	String deviceToken = "189c1558c469d683a516a09be4c7851399f721747b89dc10161b5d9a82d2ee71";

	private String keystore = "tinmanpush123.p12";

	private String password = "tinmanpush123";

	public static void main(String[] args) throws Exception
	{
		new Test().pushMsg();

	}

	public void pushMsg() throws Exception
	{
		List<PushSendDto> pushSendDtoList = new ArrayList<PushSendDto>();
		PushSendDto tempDto = new PushSendDto();
		tempDto.deviceList = new ArrayList<Device>();
		Device tempDevice = new BasicDevice();
		tempDevice.setToken(deviceToken); // 推送对象DeviceToken（开发模式与产品模式的DeviceToken不同）
		tempDto.deviceList.add(tempDevice);
		tempDto.payLoad = new PushNotificationPayload();
		tempDto.payLoad.addSound("default");
		tempDto.payLoad.addCustomAlertActionLocKey("customized button title"); // 自定义按钮标题
		tempDto.payLoad.addCustomAlertBody("Hello World!");
		tempDto.payLoad.addCustomDictionary("otherCode", "12345678");
		Calendar cal = Calendar.getInstance();
		int expiry = (int) (cal.getTimeInMillis() / 1000L) + 120;
		tempDto.payLoad.setExpiry(expiry);
		pushSendDtoList.add(tempDto);

		// 推送开始
		for (PushSendDto sendDto : pushSendDtoList)
		{
			List<PushedNotification> notifications = Push.payload(sendDto.payLoad, keystore,
					password, true, 1, sendDto.deviceList);
			PushedNotification.findSuccessfulNotifications(notifications); // 推送成功列表

			PushedNotification.findFailedNotifications(notifications); // 推送失败列表
		}
	}

}
