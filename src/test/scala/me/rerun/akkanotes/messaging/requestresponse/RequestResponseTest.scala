package me.rerun.akkanotes.messaging.requestresponse

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.ImplicitSender
import akka.testkit.TestKit
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._


/**
 * This Test case exactly does what the StudentActor was doing in the
 * requestresponse package
 *
 */
class RequestResponseTest extends TestKit(ActorSystem("TestUniversityMessageSystem"))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll 
  with ImplicitSender {

  "A teacher" must {

    "respond with a QuoteResponse when a QuoteRequest message is sent" in {

      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActorChild")
      teacherRef!QuoteRequest

      //expectMsg(QuoteResponse(_)) //Asserting that we are expecting a message back
      expectMsgPF() {

        case QuoteResponse(quoteResponse) => println(quoteResponse)
        case _ => fail("Quote response not received")

      }
      

    }

  }
  
 "A student" must {

    "sssrespond with a QuoteResponse when a QuoteRequest message is sent" in {

      import me.rerun.akkanotes.messaging.protocols.StudentProtocol._
      
      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")
      
      val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")
      studentRef!InitSignal

      //expectMsg(QuoteResponse(_)) //Asserting that we are expecting a message back
     /* expectMsgPF() {

        case QuoteResponse(quoteResponse) => println(quoteResponse)
        case _ => fail("Quote response not received")

      }*/
      

    }

  }
  override def afterAll() {
    super.afterAll()
    system.shutdown()

  }

}