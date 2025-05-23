/*
 * Copyright (c) 2015-2021 VMware Inc. or its affiliates, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.core.publisher;

import java.time.Duration;
import java.util.Objects;

import reactor.core.CoreSubscriber;
import reactor.core.Fuseable;

/**
 * @see <a href="https://github.com/reactor/reactive-streams-commons">Reactive-Streams-Commons</a>
 */
public final class MonoJust<T>
extends Mono<T>
		implements Fuseable.ScalarCallable<T>, Fuseable, SourceProducer<T>  {

	final T value;

	/**
	 * 这个构造函数不能被用户调用。只是被 {@link Mono#just(Object)} 调用。还有个 Flux 也调用。
	 */
	MonoJust(T value) {
		this.value = Objects.requireNonNull(value, "value");
	}

	@Override
	public T call() throws Exception {
		return value;
	}

	@Override
	public T block(Duration m) {
		return value;
	}

	@Override
	public T block() {
		return value;
	}

	@Override
	public void subscribe(CoreSubscriber<? super T> actual) {
		// 创建 Subscription
		// onSubscribe 通知下游准备好了
		actual.onSubscribe(Operators.scalarSubscription(actual, value));
	}

	@Override
	public Object scanUnsafe(Attr key) {
		if (key == Attr.BUFFERED) return 1;
		if (key == Attr.RUN_STYLE) return Attr.RunStyle.SYNC;
		return null;
	}
}
