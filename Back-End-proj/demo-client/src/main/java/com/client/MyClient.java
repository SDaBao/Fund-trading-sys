package com.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class MyClient {

	public static void main(String[] args) {
		CloudBootstrap.run(MyClient.class, args);

	}

}
