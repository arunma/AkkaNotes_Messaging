package me.rerun.akkanotes.messaging.actormsg1

import scala.util.Random

import akka.actor.Actor
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol.QuoteRequest
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol.QuoteResponse

/*
 * Your Teacher Actor class. 
 * 
 * The class could use refinement by way of  
 * using ActorLogging which uses the EventBus of the Actor framework
 * instead of the plain old System out
 * 
 */

class TeacherActor extends Actor {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def receive = {

    case QuoteRequest => {

      import util.Random

      //Get a random Quote from the list and construct a response
      val quoteResponse=QuoteResponse(quotes(Random.nextInt(quotes.size)))
      
      println (quoteResponse)

    }

  }

}