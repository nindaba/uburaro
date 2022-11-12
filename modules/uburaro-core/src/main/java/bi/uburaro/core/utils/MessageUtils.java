package bi.uburaro.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageUtils {
    private static final String PARAMS_SYMBOL = "\\{}";

    public static String format(String message, Supplier<?>... params){

        Queue<Supplier<?>> paramQueue = new ArrayDeque<>(List.of(params));

        return Stream.of(message.split(PARAMS_SYMBOL))
                .map(messagePart -> {
                    if (paramQueue.peek() == null) {
                        return messagePart;
                    }
                    return messagePart + paramQueue.poll().get();
                }).collect(Collectors.joining());
    }

    public static String format(String message, String... params){
        Queue<String> paramQueue = new ArrayDeque<>(List.of(params));

        return Stream.of(message.split(PARAMS_SYMBOL))
                .map(messagePart -> StringUtils.join(messagePart,paramQueue.poll()))
                .collect(Collectors.joining());
    }
}
