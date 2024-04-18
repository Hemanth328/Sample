package pbs.feop.service;

import pbs.feop.request.DashBoardResponse;

public interface IDashBoard {

	public DashBoardResponse fetchDashBoardResponse(Integer usedId);
}
