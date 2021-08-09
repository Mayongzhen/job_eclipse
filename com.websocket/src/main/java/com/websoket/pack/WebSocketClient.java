/*
 * Copyright 2014 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.websoket.pack;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;

/**
 * This is an example of a WebSocket client.
 * <p>
 * In order to run this example you need a compatible WebSocket server. Therefore you can either start the WebSocket server from the examples by running {@link io.netty.example.http.websocketx.server.WebSocketServer} or connect to an existing WebSocket server such as <a href="https://www.websocket.org/echo.html">ws://echo.websocket.org</a>.
 * <p>
 * The client will attempt to connect to the URI passed to it as the first argument. You don't have to specify any arguments if you want to connect to the example WebSocket server, as this is the default.
 */
public final class WebSocketClient {
	private static AtomicLong ID = new AtomicLong(1000000L);
	private static AtomicLong IDX = new AtomicLong(1L);

	public WebSocketClientHandler send(URI uri) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		final WebSocketClientHandler handler;
		try {
			Long userId = ID.getAndIncrement();
			handler = new WebSocketClientHandler(
				WebSocketClientHandshakerFactory.newHandshaker(
					uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders().add("Authorization", token(userId))), userId);
System.err.println("===================="+token(userId));
			Bootstrap b = new Bootstrap();
			b.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ChannelPipeline p = ch.pipeline();
						p.addLast(
							new HttpClientCodec(),
							new HttpObjectAggregator(8192),
							WebSocketClientCompressionHandler.INSTANCE,
							handler);
					}
				});

			Channel ch = b.connect(uri.getHost(), 80).sync().channel();
			handler.handshakeFuture().sync();
			ch.closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
		return handler;
	}

	private static AES aes = new AES(Mode.CBC, Padding.ZeroPadding, "efdf6b629bd989f9".getBytes(), "3891438ee6888475".getBytes());

	public static String token(Long userId) {
		String text = "com.gkid.gkid&" + userId + "&" + userId + "&" + (System.currentTimeMillis() / 1000);
		return aes.encryptBase64(text);
	}

	public static void main(String[] args) throws Exception {
		final URI URI = new URI("ws://ikid.51talk.com/api/gkids/ai_serving/ws_eval_phoneme");
		int users = 20;
		long start = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(users);
		for (int i = 0; i < users; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						for (int j = 0; j < 5; j++) {
							WebSocketClientHandler handler = null;
							try {
								handler = new WebSocketClient().send(URI);
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (handler.success()) {
								System.out.println(IDX.getAndIncrement() + ". success: " + handler.success() + ", time: " + handler.time());
							} else {
								System.err.println(IDX.getAndIncrement() + ". success: " + handler.success() + ", time: " + handler.time());
							}
						}
					} finally {
						latch.countDown();
					}
				}
			}).start();
		}
		latch.await();
		System.out.println("*** OK, time: " + (System.currentTimeMillis() - start));
	}

}