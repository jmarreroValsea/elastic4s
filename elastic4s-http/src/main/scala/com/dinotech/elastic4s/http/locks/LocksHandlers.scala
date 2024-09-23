package com.dinotech.elastic4s.http.locks

import com.dinotech.elastic4s.http._
import com.dinotech.elastic4s.locks.{AcquireGlobalLock, ReleaseGlobalLock}

trait LocksHandlers {

  implicit object AcquireGlobalLockHandler extends Handler[AcquireGlobalLock, Boolean] {

    val endpoint = "/fs/lock/global/_create"

    override def responseHandler: ResponseHandler[Boolean] = new ResponseHandler[Boolean] {
      override def handle(response: HttpResponse) = Right(response.statusCode == 201)
    }

    override def build(request: AcquireGlobalLock): ElasticRequest =
      ElasticRequest("PUT", endpoint)
  }

  implicit object ReleaseGlobalLockHandler extends Handler[ReleaseGlobalLock, Boolean] {

    override def responseHandler: ResponseHandler[Boolean] = new ResponseHandler[Boolean] {
      override def handle(response: HttpResponse) = Right(response.statusCode == 200)
    }

    override def build(request: ReleaseGlobalLock): ElasticRequest =
      ElasticRequest("DELETE", "/fs/lock/global")
  }
}
