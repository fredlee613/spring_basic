package hello.core.member;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {
    private Long id;
    private String name;
    private Grade grade;
}
