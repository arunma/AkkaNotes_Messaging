package me.rerun.akkanotes.messaging.actormsg1

import scala.util.Random

import akka.actor.Actor
import akka.actor.ActorLogging
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol.QuoteRequest
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol.QuoteResponse

/*
 * Teacher with parameters
 * 
 */

class TeacherLogParameterActor (quotes:List[String]) extends Actor with ActorLogging {

  lazy val _quotes=quotes

  def receive = {

    case QuoteRequest => {

      import util.Random

      //get a random element (for now)
      val quoteResponse=QuoteResponse(_quotes(Random.nextInt(_quotes.size)))
      log.info(quoteResponse.toString())

      //log.info("Quote printed") //This message is just to assert from the testcase      

    }

  }
      
  def quoteList=_quotes

}