package com.ai.boost.model.Resp;

import com.ai.boost.model.CarModel;
import lombok.Data;

import java.util.List;

@Data
public class BulkResp {

    int index;

    List<CarModel> carModelList;
}
