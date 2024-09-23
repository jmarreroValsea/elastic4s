package com.dinotech.elastic4s.explain

import com.dinotech.elastic4s.DocumentRef

trait ExplainApi {
  def explain(ref: DocumentRef): ExplainRequest                          = ExplainRequest(ref.indexAndType, ref.id)
  def explain(index: String, `type`: String, id: String): ExplainRequest = explain(DocumentRef(index, `type`, id))
}
