package org.bruteforce

import org.scalatest.Spec

class MaximizerSpec  extends Spec {
  describe("maximum subarray finder") {
    it("should run, at least."){}

    it("should work on this very simple array."){
      assert(BruteForce.subarray(Array(10, 3, 2)) === Tuple2(0, 2))
    }
  }
}
