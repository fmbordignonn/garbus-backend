package pucrs.ages.garbus.Utils;

import lombok.NonNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FirebaseMessage {
    private final @NonNull String subject;
    private final @NonNull String content;
}
