package com.programmerdan.minecraft.contraptions.gadget;

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

}
