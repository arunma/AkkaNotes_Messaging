package me.rerun.akkanotes.messaging.firenforget

import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._;
import akka.actor.Actor
import scala.util.Random

/*
 * Your Teacher Actor class. 
 * 
 * The class could use refinement by way of  
 * using ActorLogging which uses the EventBus of the Actor framework
 * instead of the plain old System out
 * 
 */

class TeacherPrintActor extends Actor {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def receive = {

    case QuoteRequest => {

      import util.Random

      //Print a random element (for now)
      println(quotes(Random.nextInt(quotes.size)))

    }

  }

}