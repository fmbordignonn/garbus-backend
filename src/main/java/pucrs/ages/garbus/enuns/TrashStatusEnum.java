package pucrs.ages.garbus.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrashStatusEnum {
    ATIVA(4L),
    INATIVA(5L),
    MANUTENCAO(3L),
    EXCLUIDA(6L);

    private final Long id;
}
