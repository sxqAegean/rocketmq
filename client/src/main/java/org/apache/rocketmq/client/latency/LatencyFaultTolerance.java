/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.client.latency;

public interface LatencyFaultTolerance<T> {
    /**
     * 更新失败的brokerName信息
     *
     * @param name brokerName
     * @param currentLatency 消息发送故障延迟时间
     * @param notAvailableDuration 不可用持续时长，在这段时间内broker将会被规避
     */
    void updateFaultItem(final T name, final long currentLatency, final long notAvailableDuration);

    /**
     * 校验broker是否可用
     * @param name
     * @return
     */
    boolean isAvailable(final T name);

    void remove(final T name);

    /**
     * 尝试 从 规避 的 Broker 中选 择一 个 可用 的 Broker， 如果 没有 找到， 将 返回 null。
     * @return
     */
    T pickOneAtLeast();
}
