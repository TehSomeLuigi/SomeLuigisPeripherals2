package trs.someluigi.slperiph.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import trs.org.simpleframework.http.Cookie;
import trs.someluigi.slperiph.helper.CCCHelper;
import trs.someluigi.slperiph.http.HTTPHandler;
import trs.someluigi.slperiph.ref.ModConfig;
import trs.someluigi.slperiph.struct.ListeningComputer;
import trs.someluigi.slperiph.struct.PendingRequest;
import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

public class TileEntityHTTPd extends TileEntity implements IPeripheral {
	
	public static String[] methods = new String[] {"isActive", "respond", "start", "stop", "setCookie"};
	
	@Override
	public String getType() {
		return "http-server";
	}

	@Override
	public String[] getMethodNames() {
		return TileEntityHTTPd.methods;
	}

	@Override
	public Object[] callMethod(IComputerAccess ica, ILuaContext context,
			int method, Object[] args) throws Exception {
		
		String mn = methods[method];
		
		if (mn.equals("isActive")) {
			return new Object[] {ModConfig.httpShouldRun};
		}
		
		if (mn.equals("respond")) {
			if (args.length < 2) {
				throw new RuntimeException("2 Parameters minimum for HTTP.respond");
			}
			if (args.length > 3) {
				throw new RuntimeException("3 Parameters maximum for HTTP.respond");
			}
			ListeningComputer lc = HTTPHandler.getOCByICA(ica);
			if (lc == null) {
				throw new RuntimeException("HTTP.respond requires HTTP Module to be activated with HTTP.start first");
			}
			
			Integer reqId = CCCHelper.getInt(args[0]);
			
			if (! lc.reqs.containsKey(reqId)) {
				return new Object[]{ false }; // the request does not exist
			}
			PendingRequest pr = lc.reqs.get(reqId);
			
			// add the content header
			if (args.length >= 3) {
				pr.resp.setValue("Content-type", args[2].toString());
			} else {
				pr.resp.setValue("Content-type", "text/html");
			}
			
			pr.resp.getPrintStream().print(args[1]);
			pr.resp.close();
			
			lc.reqs.remove(reqId);
			
			return new Object[]{ true };
		}
		
		if (mn.equals("setCookie")) {
			if (args.length < 3) {
				throw new RuntimeException("3 Parameters minimum for HTTP.setCookie");
			}
			if (args.length > 4) {
				throw new RuntimeException("4 Parameters maximum for HTTP.setCookie");
			}
			ListeningComputer lc = HTTPHandler.getOCByICA(ica);
			if (lc == null) {
				throw new RuntimeException("HTTP.setCookie requires HTTP Module to be activated with HTTP.start first");
			}
			
			Integer reqId = CCCHelper.getInt(args[0]);
			
			if (! lc.reqs.containsKey(reqId)) {
				return new Object[]{ false }; // the request does not exist
			}
			PendingRequest pr = lc.reqs.get(reqId);
			int expiry;
			if (args.length >= 4) {
				expiry = Integer.valueOf(args[3].toString());
			} else {
				expiry = 86400;
			}
			Cookie c = new Cookie(args[1].toString(), args[2].toString());
			c.setExpiry(expiry);
			pr.resp.setCookie(c);
			
			return new Object[]{ true };
		}
		
		if (mn.equals("start")) {
			this.addToServices(ica);
		}
		
		if (mn.equals("stop")) {
			this.removeFromServices(ica);
		}

		return null;
		
	}

	@Override
	public boolean canAttachToSide(int side) {
		return true;
	}

	@Override
	public void attach(IComputerAccess computer) {
		
	}

	@Override
	public void detach(IComputerAccess ica) {
		this.removeFromServices(ica); // if the computer loses connection with the peripheral it should hang up
	}
	
	public void addToServices(IComputerAccess ica) {
		ListeningComputer lc = new ListeningComputer();
		lc.ica = ica;
		
		HTTPHandler.openComputers.put(ica.getID(), lc);
	}
	
	public void removeFromServices(IComputerAccess ica) {
		HTTPHandler.openComputers.remove(ica.getID());
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
	}


	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
	}

}
