package com.ai.boost.model.req;

import lombok.Data;

import java.util.List;

@Data
public class BulkSearchRequest {

    private List<List<Float>> searchList;
}
