package me.rerun.akkanotes.messaging.firenforget

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.TestKit
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._

class TeacherTest extends TestKit(ActorSystem("QuoteSystem"))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A teacher" must {

    "print a quote when a QuoteRequest message is sent" in {

      val teacherRef = system.actorOf(Props[TeacherActor])
      teacherRef ! QuoteRequest

    }

  }

  "A teacher with ActorLogging" must {

    "log a quote when a QuoteRequest message is sent" in {

      val teacherRef = system.actorOf(Props[TeacherLogActor])
      teacherRef ! QuoteRequest

    }
    
  }
  
  
  

  override def afterAll() {
    super.afterAll()
    system.shutdown()

  }

}