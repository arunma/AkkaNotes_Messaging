package me.rerun.akkanotes.messaging.actormsg1

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._
import me.rerun.akkanotes.messaging.logtest2.TeacherLogActor


/**
 * Let's have the student as a simple App now instead of an Actor for the first part.
 * 
 * There's a test class which actually does the same thing as this App
 * 
 */

object StudentSimulatorApp extends App{
  
  //Initialize the ActorSystem
  val actorSystem=ActorSystem("UniversityMessageSystem")
  
  //construct the Teacher Actor Ref
  //val teacherActorRef=actorSystem.actorOf(Props[TeacherActor])
  val teacherActorRef=actorSystem.actorOf(Props[TeacherLogActor])
  
  //send a message to the Teacher Actor
  teacherActorRef!QuoteRequest
  
  //Let's wait for a couple of seconds before we shut down the system
  Thread.sleep (2000) 

  //Shut down the ActorSystem.
  actorSystem.shutdown()
  
} 


