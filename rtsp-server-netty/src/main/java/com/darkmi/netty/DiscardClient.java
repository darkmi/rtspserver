/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.darkmi.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/**
 * Keeps sending random data to the specified address.
 */
public class DiscardClient {

	private final String host;
	private final int port;
	private final int firstMessageSize;

	public DiscardClient(String host, int port, int firstMessageSize) {
		this.host = host;
		this.port = port;
		this.firstMessageSize = firstMessageSize;
	}

	public void run() {
		// Configure the client.
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline(new DiscardClientHandler(firstMessageSize));
				Timer timer = new HashedWheelTimer();
				pipeline.addLast("timeout", new IdleStateHandler(timer, 5, 5, 10));
				pipeline.addLast("idleHandler", new ClientIdleHandler());
				return pipeline;
			}
		});

		//bootstrap.setOption("allIdleTime", "5"); //这里，很重要 

		// Start the connection attempt.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));

		// Wait until the connection is closed or the connection attempt fails.
		future.getChannel().getCloseFuture().awaitUninterruptibly();

		// Shut down thread pools to exit.
		bootstrap.releaseExternalResources();
	}

	public static void main(String[] args) throws Exception {
		// Print usage if no argument is specified.
		//		if (args.length < 2 || args.length > 3) {
		//			System.err.println("Usage: " + DiscardClient.class.getSimpleName()
		//					+ " <host> <port> [<first message size>]");
		//			return;
		//		}

		//		// Parse options.
		//		final String host = args[0];
		//		final int port = Integer.parseInt(args[1]);
		//		final int firstMessageSize;
		//		if (args.length == 3) {
		//			firstMessageSize = Integer.parseInt(args[2]);
		//		} else {
		//			firstMessageSize = 256;
		//		}

		new DiscardClient("192.168.7.134", 8999, 5).run();
	}
}