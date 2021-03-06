package com.programmerdan.minecraft.contraptions.gadget;

import java.util.List;

import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.rate.PipedRate;

/**
 * Meant to be the "Input side" of a Gadget, includes methods to deal with
 * the Input Pull requirements of a Gadget, if any, and the methods to deal
 * with the metered input of the Gadget based on the Maximum Input Rate.
 * 
 * @author ProgrammerDan
 * @see {@link GadgetOutput} interface for Output Side.
 */
public interface GadgetInput {

	/**
	 * Links this input to another gadget's output.
	 * In graph terms, this defines that the Gadget Node backing the GadgetOutput
	 * interface now has a directed edge pointing to the Gadget Node backing this
	 * GadgetInput interface.
	 *
	 * In functional terms, it means that the InputPulls of this GadgetInput
	 * will draw from the OutputPushs and MaximumOutput of the connected GadgetOutput.
	 *
	 * InputLink should be established if this gadget PULLS <i>or</i> if the other
	 * gadget PUSHES. It should not be established if there is no relationship.
	 *
	 * If this Gadget does not PULL, but does PUSH, look at {@link GadgetOutput#outputLink} instead.
	 *
	 * <code>
	 *   [link] -----> [this]
	 * </code>
	 * 
	 * @param link The GadgetOutput to link to.
	 */
	public void inputLink(GadgetOutput link);

	/**
	 * Disconnects a prior link. If the link already doesn't exist, this method 
	 * does nothing.
	 *
	 * @param link the GadgetOutput to unlink.
	 */
	public void inputUnlink(GadgetOutput link);

	/**
	 * Gets the current list of input links.
	 *
	 * @return a List of GadgetOutput interface objects that all point at this input.
	 */
	public List<GadgetOutput> getInputLinks();

	/**
	 * Gets if this GadgetInput exerts a pull on Outputs attached to it. 
	 *
	 * @return true if it does exert a pull; false otherwise.
	 */
	public boolean hasInputPull();

	/**
	 * Gets the amount of pull this GadgetInput exerts over a time period, with
	 * length defined by an instance of {@link TimeMeasure}.
	 * 
	 * Implementations are free to use whatever information is available to accurately
	 * determine the pull over the requested time period.
	 *
	 * @param overTime The {@link TimeMeasure} instance indicating how long to
	 *   calculate the pull.
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over the
	 *   time period.
	 */
	public List<PipedRate> getInputPull(TimeMeasure overTime);

	/**
	 * Gets the amount of instantaneous pull this GadgetInput exerts. The basic
	 * intent is that it should map to getInputPull(TimeMeasure(1)), but it could be used
	 * in other ways -- carefully.
	 *
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   single TickMeasure unit (unless overridden).
	 */
	public List<PipedRate> getInstantInputPull();

	/**
	 * Gets if this GadgetInput has a maximum input rate. Not having a maximum input rate
	 * does NOT mean there is no input to this Gadget, it simply indicates there is no 
	 * constraint. It does mean there is no input outside of the InputPull, however.
	 * E.g. Gadgets that have a InputPull but otherwise cannot accept input should 
	 * return false.
	 *
	 * @return true if it does have a maximum input rate; false otherwise.
	 */
	public boolean hasMaximumInput();

	/**
	 * Gets the maximum input rate form from satisfiers of InputPulls and as a 
	 * result of OutputPushs over a particular time period. Any input exceeding this
	 * maximum should be ignored by Gadget algorithm managers.
	 *
	 * @param overTime The {@link TimeMeasure} instance indicating how long to
	 *   calculate the maximum.
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   time period. Will typically be a special kind of ItemStack 
	 *   indicating any item is accepted, but can allow for more complex scenarios
	 *   involving special rate limits per item type.
	 */
	public List<PipedRate> getMaximumInput(TimeMeasure overTime);

	/**
	 * Gets the amount of instantaneous nput this GadgetInput can sustain
	 * as a result of Satisfiers of InputPulls and as a result of OutputPushs.
	 * 
	 * @return A List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   single TimeMeasure unit.
	 */
	public List<PipedRate> getMaximumInstantInput();
}
