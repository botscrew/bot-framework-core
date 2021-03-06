/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.botframework.sender;

import com.botscrew.botframework.domain.user.Bot;
import com.botscrew.botframework.domain.user.Platform;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * Base implementation for sending messages to different platforms based on `Bot` parameter
 * <p>
 * Available for registration platforms via `addSender` method.
 */
public class PlatformSender {
    private final Map<Platform, Sender> platformSenders;

    public PlatformSender() {
        this.platformSenders = new EnumMap<>(Platform.class);
    }

    /**
     * Register sender for specific platform
     *
     * @param platform platform where this sender sends messages
     * @param sender   {@link Sender} implementation for specific platform
     */
    public void addSender(Platform platform, Sender sender) {
        platformSenders.put(platform, sender);
    }

    public void send(Bot bot, Message message) {
        platformSenders.get(bot.getPlatform()).send(bot, message);
    }

    public ScheduledFuture send(Bot bot, Message message, int delay) {
        return platformSenders.get(bot.getPlatform()).send(bot, message, delay);
    }
}
