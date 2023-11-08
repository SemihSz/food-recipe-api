package com.countries_api.task;

import java.util.function.BiFunction;

public interface Mappers<T, R, S> extends BiFunction<T, R, S> {
}
