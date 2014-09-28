package me.rerun.akkanotes.messaging.firenforget

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.TestKit
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._
import akka.testkit.TestActorRef
import me.rerun.akkanotes.messaging.firenforget.TeacherLogActor

class TeacherPreTest extends TestKit(ActorSystem("UniversityMessageSystem"))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

 
  "A teacher with ActorLogging" must {

    "log a quote when a QuoteRequest message is sent" in {

      val teacherRef = TestActorRef[TeacherLogActor]
      teacherRef ! QuoteRequest

    }
  }
  
  override def afterAll() {
    super.afterAll()
    system.shutdown()

  }
}



