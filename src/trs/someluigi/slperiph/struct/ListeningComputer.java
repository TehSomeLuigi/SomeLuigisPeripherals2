package trs.someluigi.slperiph.struct;

import java.util.HashMap;

import dan200.computer.api.IComputerAccess;

public class ListeningComputer {
	
	public IComputerAccess ica;
	public HashMap<Integer, PendingRequest> reqs = new HashMap<Integer, PendingRequest>();
	
}
