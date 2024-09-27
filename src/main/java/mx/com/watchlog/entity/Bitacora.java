package mx.com.watchlog.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bitacora")
@ToString(includeFieldNames = true)
@Builder
public class Bitacora {
    @Id
    private String id;
    private String turno;
    private Date fecha;
    private String horaIncidente;
    private String tipoIncidente;
    private String descripcion;
    private String informacionAdicional;
}
