package com.idb.tpbank.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpPayload {
    private String id;

    private String url;

    private String user;

    private String fullname;

    private String type;

    private String ghiChu;

    private String nguon;

    private String fkChienDich;
}
