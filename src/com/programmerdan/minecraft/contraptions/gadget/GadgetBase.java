package com.programmerdan.minecraft.contraptions.gadget;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

import com.programmerdan.minecraft.contraptions.rate.PipedRate;
import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.util.AdvItemStack;

/**
 * Base implementation of GadgetInput and GadgetOutput contracts, including
 * other graph-focused functions necessary for whatever algorithm controls
 * the actual "ticking" or iteration of a Gadget. 
 * 
 * Note from an abstract design perspective, I'm focusing on Node, Edge (Internode),
 * and Algorithm, in that order (roughly). So, the Node is at the core of it, and
 * knows its Edges (generally). It doesn't "know" anything beyond that. So, all
 * "next state" computation is based on that local knowledge. It's up to the
 * algorithm to determine safe "step sizes" based on its global knowledge. This
 * is a middle-path I plan to walk given the cycles and other artifacts of 
 * directed graphs that will exist as a result of getting this to work at all.
 *
 * Note that there will be some special case gadgets that act as pure passthroughs,
 * such as Pipes, and I'm still trying to figure out exactly how to represent them
 * as they could become effective "forwarders" of pushes into pulls, and reverse...
 * 
 * Much TODO here, but the framing in code has begun.
 *
 * @author ProgrammerDan
 * @since 1.0.0 September 2015
 */
public abstract class GadgetBase implements GadgetInput, GadgetOutput {
	/**
	 * Indicator if this Gadget has a storage component or not.
	 * @return true if this Gadget has public storage, false otherwise.
	 */
	public abstract boolean hasStorage();
	
	/**
	 * Adjusts the storage based on flow rate INTO the storage against flow rate
	 * OUT OF the storage. Anything that can't fit is discarded. Implementations that
	 * are waste-aware should use the {@link #emulateAdjustStorage(List, List, TimeMeasure)} first
	 * to see how much waste will occur and adjust runtime accordingly.
	 * 
	 * @param inflow The List of PipedRate elements flowing into the storage, as measured over time. Assumption is that flowrate
	 *   is fixed for this period; e.g. PipedRate is not a complex function, but a constant, over the given time.
	 * @param outflow The List of PipedRate elements flowing out of the storage, as measured over time.
	 * @param time The amount of time that elements flow in and out of the storage, given the PipedRates.
	 */
	public abstract void adjustStorage(List<PipedRate> inflow,
			List<PipedRate> outflow, TimeMeasure time);
	
	/**
	 * See {@link #adjustStorage(List, List, TimeMeasure)} for an explanation; convenience
	 * method that effectively calls adjustStorage with the rates given over a single instant.
	 * @param inflow
	 * @param outflow
	 */
	public abstract void adjustStorageInstant(List<PipedRate> inflow, 
			List<PipedRate> outflow);
	
	/**
	 * Gets the *instantaneous* storage as it stands after any in-progress adjustments resolve.
	 * This may block depending on implementations.
	 * 
	 * @return List of AdvItemStack objects indicating the current storage.
	 */
	public abstract List<AdvItemStack> getStorage();
	
	/**
	 * Emulates, but does not apply, the effect of the inflow and outflow over time (as described in
	 * {@link #adjustStorage(List, List, TimeMeasure)}) on this storage. The resolution
	 * of all pending in-progress adjustments must occur before this emulation can occur; thus 
	 * blocking may be necessary. Does not actually alter the internal storage, but returns a
	 * representation of the internal storage as it would appear should this adjustment be applied.
	 * 
	 * @param inflow
	 * @param outflow
	 * @param time
	 * @return an emulation of {@link #getStorage()} as if the adjustment had occurred. 
	 */
	public abstract List<AdvItemStack> emulateAdjustStorage(
			List<PipedRate> inflow, List<PipedRate> outflow, TimeMeasure time);
	
	/**
	 * Given an inflow and outflow, returns the amount of time this can be sustained before
	 * (1) the Storage runs out of space or (2) the Storage runs out of items. 
	 * <br>
	 * If the time returned has a length of 0, means these flowrates cannot be sustained at all.
	 * <br>
	 * If the time returned has a length of {@link Double#POSITIVE_INFINITY}, means this flowrate
	 * can be sustained indefinitely.
	 *  
	 * @param inflow
	 * @param outflow
	 * @return
	 */
	public abstract TimeMeasure timeFitAdjustStorage(
			List<PipedRate> inflow, List<PipedRate> outflow);
	
	/**
	 * Some types of gadgets have "private" or internal storage. It is not accessible
	 * to players, but can serve as a sink for inputs or a source for outputs.
	 * 
	 * @return true if this gadget has a private storage, false otherwise.
	 */
	public abstract boolean hasPrivateStorage();
	public abstract void adjustPrivateStorage(List<PipedRate> inflow,
			List<PipedRate> outflow, TimeMeasure time);
	public abstract void adjustPrivateStorageInstant(List<PipedRate> inflow, 
			List<PipedRate> outflow);
	public abstract List<AdvItemStack> getPrivateStorage();

	public abstract List<AdvItemStack> emulateAdjustPrivateStorage(
			List<PipedRate> inflow, List<PipedRate> outflow, TimeMeasure time);
	
	public abstract TimeMeasure timeFitAdjustPrivateStorage(
			List<PipedRate> inflow, List<PipedRate> outflow);
	
	/* TODO: 
	 *   * Gadget creation helper from configuration (Factory preferred)
	 *     * Factory should generate Times/Pipedrates
	 *     * Factory should figure out what kind of Gadget and wire it up
	 *   * Need location, type, mode indicator
	 *     * Active
	 *     * Inactive
	 *   * UI stubs for interaction and control
	 *   * save structure/serialization structure for contraption
	 *     * should preserve momentary state
	 *     * for server restart
	 *   * consider how to run unloaded contraptions
	 *     * fast-forward on load? **** 
	 *     * always active?
	 *     * split the difference? could need both.
	 */
	
	private final Location location;
	private final Material type;
}
