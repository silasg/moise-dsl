/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package moise.dsl.fs

abstract class SchemeElement {
  def parallel_with (s: SchemeElement) = Parallel(this :: s :: Nil)
  def or (s: SchemeElement) = Choice(this :: s :: Nil)
  def then (s: SchemeElement) = Sequence(this :: s :: Nil)
}
