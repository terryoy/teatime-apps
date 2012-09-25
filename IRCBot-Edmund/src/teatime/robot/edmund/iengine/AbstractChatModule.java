package teatime.robot.edmund.iengine;


public abstract class AbstractChatModule implements ChatModule {

	private IntelligenceEdmund iEdmund;
	
	public AbstractChatModule(IntelligenceEdmund iEdmund) {
		this.iEdmund = iEdmund;
	}
	
	protected IntelligenceEdmund getEdmund() {
		return this.iEdmund;
	}
}
