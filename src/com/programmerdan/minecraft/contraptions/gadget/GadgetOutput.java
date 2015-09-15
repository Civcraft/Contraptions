package com.programmerdan.minecraft.contraptions.gadget;

import java.util.List;

import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.time.TickMeasure;

/**
 * Meant to be the "Output side" of a Gadget, includes methods to deal with
 * the Output Push requirements of a Gadget, if any, and the methods to deal
 * with the metered output of the Gadget based on the Maximum Output Rate.
 * 
 * @author ProgrammerDan
 * @see {@link GadgetInput} interface for Input Side.
 */
public interface GadgetOutput {

	/**
	 * Links this output to another gadget's input.
	 * In graph terms, this defines that the Gadget Node backing the GadgetInput
	 * interface now has a directed edge pointing to the Gadget Node backing this
	 * GadgetOutput interface.
	 *
	 * In functional terms, it means that the OutputPushes of this GadgetOutput
	 * will push to the InputPulls and MaximumInput of the connected GadgetInput.
	 *
	 * OutputLink should be established if this gadget PUSHES <i>or</i> if the other
	 * gadget PULLS. It should not be established if there is no relationship.
	 *
	 * If this Gadget does not PUSH, but does PULL, look at {@link GadgetInput#inputLink} instead.
	 *
	 * <code>
	 *   [link] -----> [this]
	 * </code>
	 * 
	 * @param link The GadgetInput to link to.
	 */
	public void outputLink(GadgetInput link);

	/**
	 * Disconnects a prior link. If the link already doesn't exist, this method 
	 * does nothing.
	 *
	 * @param link the GadgetInput to unlink.
	 */
	public void outputUnlink(GadgetInput link);

	/**
	 * Gets the current list of output links.
	 *
	 * @return a List of GadgetInput interface objects that all point at this output.
	 */
	public List<GadgetInput> getOutputLinks();

	/**
	 * Gets if this GadgetOutput exerts a push on Inputs attached to it. 
	 *
	 * @return true if it does exert a push; false otherwise.
	 */
	public boolean hasOutputPush();

	/**
	 * Gets the amount of push this GadgetOutput exerts over a time period, with unit and
	 * length defined by an instance of a subclass of {@link TimeMeasure}.
	 * 
	 * Implementations are free to use whatever information is available to accurately
	 * determine the push over the requested time period.
	 * @param <T>
	 *
	 * @param overTime The {@link TimeMeasure} subclass instance indicating how long to
	 *   calculate the push.
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   time period as a TimeMeasure.
	 */
	public <T extends TimeMeasure> List<PipedRate<T>> getOutputPush(T overTime);

	/**
	 * Gets the amount of instantaneous (per tick) push this GadgetOutput exerts. The basic
	 * intent is that it should map to getOutputPush(TickMeasure(1)), but it could be used
	 * in other ways -- carefully.
	 *
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   single TickMeasure unit (unless overridden).
	 */
	public List<PipedRate<TickMeasure>> getOutputPushPerTick();

	/**
	 * Gets if this GadgetOutput has a maximum output rate. Not having a maximum output rate
	 * does NOT mean there is no output from this Gadget, it simply indicates there is no 
	 * constraint. It does mean there is no output outside of the OutputPush, however.
	 * E.g. Gadgets that have a OutputPush but otherwise cannot offer output should 
	 * return false.
	 *
	 * @return true if it does have a maximum output rate; false otherwise.
	 */
	public boolean hasMaximumOutput();

	/**
	 * Gets the maximum output rate from satisfiers of OutputPushes and as a 
	 * result of InputPulls over a particular time period. Any output exceeding this
	 * maximum should be ignored by Gadget algorithm managers.
	 *
	 * @param overTime The {@link TimeMeasure} subclass instance indicating how long to
	 *   calculate the maximum.
	 * @return a List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   time period as a TimeMeasure. Will typically be a special kind of ItemStack 
	 *   indicating any item is accepted, but can allow for more complex scenarios
	 *   involving special rate limits per item type.
	 */
	public <T extends TimeMeasure> List<PipedRate<T>> getMaximumOutput(T overTime);

	/**
	 * Gets the amount of instantaneous (per tick) input this GadgetOutput can sustain
	 * as a result of Satisfiers of OutputPushes and as a result of InputPulls.
	 * 
	 * @return A List of {@link PipedRate} instances indicating an ItemStack rate over a
	 *   single TickMeasure unit (unless overridden).
	 */
	public List<PipedRate<TickMeasure>> getMaximumOutputPerTick();
}
