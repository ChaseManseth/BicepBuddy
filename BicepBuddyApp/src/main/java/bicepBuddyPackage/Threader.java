package bicepBuddyPackage;

import Views.LoadingView;

public class Threader implements Runnable{

	
	
	public Threader() {
		
	}
	
	@Override
	public void run() {
		//thread the loading screen
		//****LOADING SCREEN ****
    	Master.getInstance().updateFrame(new LoadingView());
	}

}
