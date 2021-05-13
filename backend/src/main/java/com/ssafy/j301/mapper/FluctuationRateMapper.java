package com.ssafy.j301.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ssafy.j301.fluctuationRate.FluctuationRate;

@Repository
public interface FluctuationRateMapper {

	public List<FluctuationRate> selectRateOfRise();

	public List<FluctuationRate> selectRateOfDecline();

}
