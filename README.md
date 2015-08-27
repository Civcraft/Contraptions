Contraptions REBOOT
======================

After serious thought and reflection on the existing infrastructure offered in gmlaxfanatic's FactoryMod Reboot, I've decided to go a slightly different direction. This document will be the design document that all contributors need to adher to as we work together to develop a new Contraptions that is flexible enough to both cover existing factories and whatever new technologies we want to unlock in the future, without tying us to arbitrary monolithic fixed format structures. Instead, let's empower the players to be creative and give full flexibility without sacrificing our ability to run factories without direct player involvement.

How can we do this?

  * Acyclic graphs
  * Nodes that represent Inputs, Transforms, and Outputs (yep, that's it)
  * Arbitrary configurations of Nodes in "Real-space"
  * Extension of edges between nodes via Pipes
  * Comprehensive Server-Delivered UI experience

Okay. Sounds fancy and / or stupid complex, how is this all going to work?

Well, first, we need to put some limits on the types of nodes that can live within our Contraption framework. To do that, let's start with some definitions.

### Definitions

  **Contraption**: An Acyclic graph involving at least one active Gadget. Contraptions can be combined into larger Contraptions.

  **Gadget**: A Node in the graph

  **Pipe**: A color-coded link between Gadgets if they cannot physically touch

  **Contraption Mode**: An active user mode (similar to Citadel or Bastions) allowing placement, manipulation, and control of Gadgets

  **Gadget UI**: A guided User Interaction for a Gadget. Upon placement allows the choice of "type" based on the block placed; provides feedback on the costs needed to "build" the gadget and consumption of costs once collected. Allows updates to a placed Gadget, perhaps unlocking new features, improving existing ones, or other advanced functions.

  **Item**: A Minecraft item, perhaps with custom lore

  **Resource**: A virtual item, only has meaning in the context of Contraptions. Could be "power", "magic" or other abstract non-realized properties

  **Input**: A quantity consumed, represented in terms of Amount of Type per Time Period, or Amount / Time

  **Output**: A quantity produced, represented in terms of Amount of Type per Time Period, or Amount / Time.

  **Transform**: The "glue" between Input and Output, expressed as conditions on input or output or both, including limiters or suppressions (e.g. output is suppressed if input is not fully realized, or output is slowed if input is only met after a longer time period)

  **Citadel**: Citadel respect is important. Initially, any Gadget or Pipe reinforced to a group must be in the same group to be part of the same Contraption. Eventually, we could leverage Public groups to allow "public" power infrustructure to emerge (potentially), perhaps by allowing certain types of resources to cross group boundaries under specific conditions.

  **RealisticBiomes**: Will get into this more lately, but a close relationship to Realistic Biomes may be necessary to handle the "farming" contraptions. Realistic Biomes allows complete control over the ability of crops, trees, and other elements to be spawned within particular biomes, or how "well" they spawn.

Alright, got it? Good. Let's talk about the types of Gadgets we can support, and what kind of features they might have.

### Gadgets

There are six basic kinds of Gadgets. They are the Container, the Collector, the Converter, the Consumer, the Marker, and the Totem. It is possible that additional kinds of Gadgets may be necessary, but this set of six is a good start.

Let's break them down.

#### Container

A container must be represented by a Minecraft block with some kind of inventory. 

All containers accept inputs of any Items, until they are full, at which point they stop accepting input.

All containers have limits in input/output quantity per second.

Containers do not push their output; it is available but does not move unless "pulled" by something that desires a specific input.

Containers do not pull on their inputs; only inputs that are pushed into the container enter the container.

Some types of containers might be able to store Resources, but non-retrievable by a player. E.g. a Battery (stores Power)

#### Collector

A collector is what hoppers should have been; they can be represented by any minecraft block without an inventory.

Collectors are active components and must be powered by a Resource.

Collectors pull items from inputs and push to outputs. 

Collectors could be limited to a specific item, resource.

Collectors will have a limit on throughput; Items / second.

Collectors have no storage; if they cannot push items to output, they do not pull items from inputs.

Collectors will consume power Resources whether pushing or pulling or not, so long as power is available.

Collectors are the only way to extract resources from Marker Pairs or Totems (explained later).

#### Converter

A converter can be a Minecraft block with or without some kind of inventory (for instance, a Furnace, or a Crafting Table).

Converters are active components and generally must be powered. 

The exception is a Generator, which doesn't need to be powered but does convert Items into power Resources. 

Output is pushed, but whatever isn't taken when presented is lost (e.g. a generator that overgenerates power is wasteful, or a converter that is connected to insufficient storage is lossy).

Converters can optionally be controlled by linked code; a Class name in the config pointing to a Class definition following a specific interface would be necessary in place of "generic" transformations. This would be useful in cases like Repair Factory, where it might be necessary to have highly custom handling of "Conversions".

#### Consumer

A consumer simply consumes inputs. In response, it produces some effect. 

That effect is controlled by linked code; a Class name following a specific interface must be declared in the config. This allows infinite specification of effects, as listeners fully Contraption aware can be constructed.

For instance, a Teleportation Consumer could be created, that while powered allows teleportation. It can do this as attached to the Consumer is onCreate code that generates a teleportation platform in one world to another world, and binds a PlayerInteractionEvent listener to monitor for hits on that block with a Stick,

Another example would be a Bastion Suppression field. While powered, could generate a suppressing effect on nearby bastions. Would require a certain amount of bukkitfu to code, to tie into Bastions.

#### Marker

A marker is always part of a paired set, and together they mark out a volume of MC space.

Depending on the quality/expense associated with the base marker, depends on the volume limitations of a marker set.

The markers once established continuously produce based on the resources they encompass.

Markers consume power to produce output

Markers do not "push" their output, nor do they store it, so it is important that they be connected to a Collector.

Markers produce output based primarily on the blocks they subsume; if mature wheat, produces wheat, if mature pumpkin stems, pumpkins, etc.

Ideally works with Realistic Biomes to suppress height growth and mushroom spread, perhaps even pumpkin & melon sprouting; instead all the drops that would have occurred are "captured" by the Marker.

#### Totem

A totem is a subdivision version of the marker. 

It functions exactly the same, but its area of control is voronoi, or some nearness model, or other as yet undiscussed spatial subdivision model.

## Considerations

Note that I've made some distinction between passive "availability" to output or to input, vs. actively "pushing" to output or "pulling" from input. This may not work out in practice. In general, pre-computability, or the ability to unload the contraption and it still "work" as if it were on the whole time, is paramount, and all other considerations are secondary.

An example of this in practice: if two Containers are connected, they do not push or pull from each other. Their "inputs" and "outputs" are passive, not active. However, if a Collector were placed at the end of a container, every container "connected" to the collector would potentially receive items from the collector in a "fanout" fashion. Determining "source" vs. "receipt" is the job of the graph traversal function, which will use a marking function to enforce the a-cyclic rule.

Another consideration is that every face of a Gadget's physical form, generally, is both an input and output. There may be specific limitations to this; for instance, it may be that a generic Collector's top face is strictly input, and all other faces are either. This would allow the graph construction function to explicitly order the nodes in the graph.

I haven't focused on it, but another consideration is factory wear and tear. I'm not a fan of arbitrarily decrementing the factory's health "because it was used" -- Generally speaking, equipment does run down over time, so time-based culling makes sense. Overuse situations IRL can lead to damaged equipment, doesn't make a heap of sense in MC terms. I propose an alternative; a Consumer gadget that has an inventory, consumes Resource:Fuel and reverses the tide of entropic decay if it also has necessary repair materials in its inventory to consume alongside the Resource. Behind the scenes, while running the repair, the graph is traversed and for each component in the graph, a 1/N (where N is number of nodes) portion of the generated Resource:Repair is applied to each Gadget. If a Gadget is fully decayed, the Repair gadget will not revive it. You must interact with that block directly, paying a local cost via the UI (speculation!).

## Specific Examples

Consider that the world is currently full of "Factories" -- to ensure the transition to Contraptions is as smooth as possible, it would be nice if all the current Factories can be seemlessly converted to Contraptions. This can be achieved as follows:

Consider the Furnace to be a Converter of type Generator. It is specialized to consume Charcoal and generates Resource:Power, at a rate of 1 Charcoal per second to produce 1 Resource:Power; in other terms, it produces 1 Resource:Power per second while consuming Charcoal at a rate of 1 Charcoal per second.

Consider the Crafting Bench to be a Converter with a number of recipe specializations. Only a single Recipe can be active at a time; it can be selected via the Contraptions UI. Each recipe will have a specific input requirement in terms of Resource:Power per second and Items per second, yeilding specific output Items per second. These would be rebalanced recipes from FactoryMod; direct conversion is unlikely (although not impossible).

Finally, consider the Chest to be the Container. It is unspecialized. 

In this way, the Generator produces power for the Converter; the Converter pulls Items from the Chest, and Resource:Power from the Generator. It pushes Items into the Chest as they are converted. All the major Production Factories can be modelled in this fashion, including Printing Press, Compactor, and Repair (broadly). The contraption is turned on by activating the Generator, and turns off when the Generator runs out of fuel (biggest difference from current FactoryMod).

# Technical Details

Abstract Class Gadget

Interface Container

Class Storage

Class Battery

Interface Collector

Class GeneralCollector

Class ItemLockedCollector

Class ResourceLockedCollector

Interface Converter

Class Generator

Class GeneralConverter

Interface Consumer

Class Lubricator

Class GeneralConsumer

Interface Marker

Class BindableMarker

class ConnectedMarker

Interface Totem

Class GeneralTotem

Interface Flowable

Abstract Class Resource

Class Power

Abstract Class Item

Class SpigotItem

