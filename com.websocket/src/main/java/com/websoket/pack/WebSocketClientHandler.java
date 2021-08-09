/*
 * Copyright 2012 The Netty Project
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
//The MIT License
//
//Copyright (c) 2009 Carl Bystršm
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in
//all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//THE SOFTWARE.

package com.websoket.pack;

import cn.hutool.core.io.FileUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.netty.util.CharsetUtil;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

	private final WebSocketClientHandshaker handshaker;
	private ChannelPromise handshakeFuture;
	private String file = "f:/tall.mp3";
	private long startTime = System.currentTimeMillis();
	private boolean success = false;
	private Long userId;

	public WebSocketClientHandler(WebSocketClientHandshaker handshaker, Long userId) {
		this.handshaker = handshaker;
		this.userId = userId;
	}

	public ChannelFuture handshakeFuture() {
		return handshakeFuture;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		handshakeFuture = ctx.newPromise();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		handshaker.handshake(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		//System.out.println("WebSocket Client disconnected!");
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel ch = ctx.channel();
		if (!handshaker.isHandshakeComplete()) {
			try {
				handshaker.finishHandshake(ch, (FullHttpResponse) msg);
				// System.out.println("WebSocket Client connected!");
				handshakeFuture.setSuccess();

				ctx.writeAndFlush(new TextWebSocketFrame("{\"text\":\"tall\",\"event_id\":\"32\",\"event_type\":\"audio\",\"gkidno\":\"" + this.userId + "\",\"is_correction\":false,\"keywords\":\"\",\"phonemes\":\"T AO L\",\"raw_json_param\":\"{\\\"brand\\\":\\\"HUAWEI\\\",\\\"manufacturer\\\":\\\"HUAWEI\\\",\\\"model\\\":\\\"ELE-AL00\\\",\\\"version\\\":\\\"29\\\"}\",\"save\":true,\"session_type\":\"ReadAfterMeModule_L0\",\"uid\":\"" + this.userId + "\",\"version\":\"3.2.5\"}"));
				Thread.sleep(100);
				ctx.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(FileUtil.readBytes(file))));
				// ctx.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(FileUtil.readBytes(file))));
				// ctx.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(FileUtil.readBytes(file))));
				//System.out.println("--- send ok");

			} catch (WebSocketHandshakeException e) {
				//System.out.println("WebSocket Client failed to connect");
				handshakeFuture.setFailure(e);
			}
			return;
		}

		if (msg instanceof FullHttpResponse) {
			FullHttpResponse response = (FullHttpResponse) msg;
			throw new IllegalStateException(
				"Unexpected FullHttpResponse (getStatus=" + response.status() +
					", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
		}

		WebSocketFrame frame = (WebSocketFrame) msg;
		if (frame instanceof TextWebSocketFrame) {
			TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
			String text = textFrame.text();
			// System.out.println("WebSocket Client received message: " + text);
			if (text.startsWith("{") && text.contains("score")) {
				success = true;
				System.out.println(text);
			} else {
				// System.out.println("failed");
			}
			ch.close();
		} else if (frame instanceof PongWebSocketFrame) {
			// System.out.println("WebSocket Client received pong");
		} else if (frame instanceof CloseWebSocketFrame) {
			// System.out.println("WebSocket Client received closing");
			ch.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		if (!handshakeFuture.isDone()) {
			handshakeFuture.setFailure(cause);
		}
		ctx.close();
	}

	public long time() {
		return System.currentTimeMillis() - this.startTime;
	}

	public boolean success() {
		return success;
	}

}