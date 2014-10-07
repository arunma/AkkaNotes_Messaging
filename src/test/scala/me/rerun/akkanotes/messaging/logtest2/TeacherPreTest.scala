package me.rerun.akkanotes.messaging.logtest2

import org.scalatest.BeforeAndAfterAll
import org.scalatest.MustMatchers
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import akka.testkit.TestKit

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



