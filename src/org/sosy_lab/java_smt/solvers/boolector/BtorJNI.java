/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.sosy_lab.java_smt.solvers.boolector;

/**
 * Native code for Boolector methods.
 */
class BtorJNI {

  protected final static native int BOOLECTOR_PARSE_ERROR_get();

  protected final static native int BOOLECTOR_PARSE_UNKNOWN_get();

  protected final static native long boolector_new();

  protected final static native long boolector_clone(long jarg1);

  protected final static native void boolector_delete(long jarg1);

  protected final static native void boolector_set_term(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_terminate(long jarg1);

  protected final static native void boolector_set_abort(long jarg1);

  protected final static native void boolector_set_msg_prefix(long jarg1, String jarg2);

  protected final static native long boolector_get_refs(long jarg1);

  protected final static native void boolector_reset_time(long jarg1);

  protected final static native void boolector_reset_stats(long jarg1);

  protected final static native void boolector_print_stats(long jarg1);

  protected final static native void boolector_set_trapi(long jarg1, long jarg2);

  protected final static native long boolector_get_trapi(long jarg1);

  protected final static native void boolector_push(long jarg1, long jarg2);

  protected final static native void boolector_pop(long jarg1, long jarg2);

  protected final static native void boolector_assert(long jarg1, long jarg2);

  protected final static native void boolector_assume(long jarg1, long jarg2);

  protected final static native boolean boolector_failed(long jarg1, long jarg2);

  protected final static native long boolector_get_failed_assumptions(long jarg1);

  protected final static native void boolector_fixate_assumptions(long jarg1);

  protected final static native void boolector_reset_assumptions(long jarg1);

  protected final static native long boolector_sat(long jarg1);

  protected final static native long boolector_limited_sat(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_simplify(long jarg1);

  protected final static native void boolector_set_sat_solver(long jarg1, String jarg2);

  protected final static native void boolector_set_opt(long jarg1, int jarg2, long jarg3);

  protected final static native long boolector_get_opt(long jarg1, int jarg2);

  protected final static native long boolector_get_opt_min(long jarg1, int jarg2);

  protected final static native long boolector_get_opt_max(long jarg1, int jarg2);

  protected final static native long boolector_get_opt_dflt(long jarg1, int jarg2);

  protected final static native String boolector_get_opt_lng(long jarg1, int jarg2);

  protected final static native String boolector_get_opt_shrt(long jarg1, int jarg2);

  protected final static native String boolector_get_opt_desc(long jarg1, int jarg2);

  protected final static native boolean boolector_has_opt(long jarg1, int jarg2);

  protected final static native int boolector_first_opt(long jarg1);

  protected final static native int boolector_next_opt(long jarg1, int jarg2);

  protected final static native long boolector_copy(long jarg1, long jarg2);

  protected final static native void boolector_release(long jarg1, long jarg2);

  protected final static native void boolector_release_all(long jarg1);

  protected final static native long boolector_true(long jarg1);

  protected final static native long boolector_false(long jarg1);

  protected final static native long boolector_implies(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_iff(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_eq(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ne(long jarg1, long jarg2, long jarg3);

  protected final static native boolean boolector_is_bv_const_zero(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bv_const_one(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bv_const_ones(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bv_const_max_signed(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bv_const_min_signed(long jarg1, long jarg2);

  protected final static native long boolector_const(long jarg1, String jarg2);

  protected final static native long boolector_constd(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_consth(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_zero(long jarg1, long jarg2);

  protected final static native long boolector_ones(long jarg1, long jarg2);

  protected final static native long boolector_one(long jarg1, long jarg2);

  protected final static native long boolector_min_signed(long jarg1, long jarg2);

  protected final static native long boolector_max_signed(long jarg1, long jarg2);

  protected final static native long boolector_unsigned_int(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_int(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_var(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_array(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_uf(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_not(long jarg1, long jarg2);

  protected static native long boolector_neg(long jarg1, long jarg2);

  protected final static native long boolector_redor(long jarg1, long jarg2);

  protected final static native long boolector_redxor(long jarg1, long jarg2);

  protected final static native long boolector_redand(long jarg1, long jarg2);

  protected final static native long
      boolector_slice(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native long boolector_uext(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sext(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_xor(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_xnor(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_and(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_nand(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_or(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_nor(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_add(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_uaddo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_saddo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_mul(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_umulo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_smulo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ult(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_slt(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ulte(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_slte(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ugt(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sgt(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ugte(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sgte(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sll(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_srl(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sra(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_rol(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ror(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sub(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_usubo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_ssubo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_udiv(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sdiv(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_sdivo(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_urem(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_srem(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_smod(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_concat(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_repeat(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_read(long jarg1, long jarg2, long jarg3);

  protected final static native long
      boolector_write(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native long boolector_cond(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native long boolector_param(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_fun(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native long
      boolector_apply(long jarg1, long[] jarg2, long jarg3, long jarg4);

  protected final static native long boolector_inc(long jarg1, long jarg2);

  protected final static native long boolector_dec(long jarg1, long jarg2);

  protected final static native long
      boolector_forall(long jarg1, long[] jarg2, long jarg3, long jarg4);

  protected final static native long
      boolector_exists(long jarg1, long[] jarg2, long jarg3, long jarg4);

  protected final static native long boolector_get_btor(long jarg1);

  protected final static native long boolector_get_node_id(long jarg1, long jarg2);

  protected final static native long boolector_get_sort(long jarg1, long jarg2);

  protected final static native long boolector_fun_get_domain_sort(long jarg1, long jarg2);

  protected final static native long boolector_fun_get_codomain_sort(long jarg1, long jarg2);

  protected final static native long boolector_match_node_by_id(long jarg1, long jarg2);

  protected final static native long boolector_match_node_by_symbol(long jarg1, String jarg2);

  protected final static native long boolector_match_node(long jarg1, long jarg2);

  protected final static native String boolector_get_symbol(long jarg1, long jarg2);

  protected final static native void boolector_set_symbol(long jarg1, long jarg2, String jarg3);

  protected final static native long boolector_get_width(long jarg1, long jarg2);

  protected final static native long boolector_get_index_width(long jarg1, long jarg2);

  protected final static native String boolector_get_bits(long jarg1, long jarg2);

  protected final static native void boolector_free_bits(long jarg1, String jarg2);

  protected final static native long boolector_get_fun_arity(long jarg1, long jarg2);

  protected final static native boolean boolector_is_const(long jarg1, long jarg2);

  protected final static native boolean boolector_is_var(long jarg1, long jarg2);

  protected final static native boolean boolector_is_array(long jarg1, long jarg2);

  protected final static native boolean boolector_is_array_var(long jarg1, long jarg2);

  protected final static native boolean boolector_is_param(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bound_param(long jarg1, long jarg2);

  protected final static native boolean boolector_is_uf(long jarg1, long jarg2);

  protected final static native boolean boolector_is_fun(long jarg1, long jarg2);

  protected final static native long
      boolector_fun_sort_check(long jarg1, long[] jarg2, long jarg3, long jarg4);

  protected final static native String boolector_bv_assignment(long jarg1, long jarg2);

  protected final static native void boolector_free_bv_assignment(long jarg1, String jarg2);

  protected final static native void
      boolector_array_assignment(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5);

  protected final static native void
      boolector_free_array_assignment(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native void
      boolector_uf_assignment(long jarg1, long jarg2, long jarg3, long jarg4, long jarg5);

  protected final static native void
      boolector_free_uf_assignment(long jarg1, long jarg2, long jarg3, long jarg4);

  protected final static native void boolector_print_model(long jarg1, String jarg2, long jarg3);

  protected final static native long boolector_bool_sort(long jarg1);

  protected final static native long boolector_bitvec_sort(long jarg1, long jarg2);

  protected final static native long
      boolector_fun_sort(long jarg1, long[] jarg2, long jarg3, long jarg4);

  protected final static native long boolector_array_sort(long jarg1, long jarg2, long jarg3);

  protected final static native long boolector_copy_sort(long jarg1, long jarg2);

  protected final static native void boolector_release_sort(long jarg1, long jarg2);

  protected final static native boolean boolector_is_equal_sort(long jarg1, long jarg2, long jarg3);

  protected final static native boolean boolector_is_array_sort(long jarg1, long jarg2);

  protected final static native boolean boolector_is_bitvec_sort(long jarg1, long jarg2);

  protected final static native boolean boolector_is_fun_sort(long jarg1, long jarg2);

  protected final static native long
      boolector_parse(long jarg1, long jarg2, String jarg3, long jarg4, long jarg5, long jarg6);

  protected final static native long boolector_parse_btor(
      long jarg1,
      long jarg2,
      String jarg3,
      long jarg4,
      long jarg5,
      long jarg6);

  protected final static native long boolector_parse_btor2(
      long jarg1,
      long jarg2,
      String jarg3,
      long jarg4,
      long jarg5,
      long jarg6);

  protected final static native long boolector_parse_smt1(
      long jarg1,
      long jarg2,
      String jarg3,
      long jarg4,
      long jarg5,
      long jarg6);

  protected final static native long boolector_parse_smt2(
      long jarg1,
      long jarg2,
      String jarg3,
      long jarg4,
      long jarg5,
      long jarg6);

  protected final static native void boolector_dump_btor_node(long jarg1, long jarg2, long jarg3);

  protected final static native void boolector_dump_btor(long jarg1, long jarg2);

  protected final static native void boolector_dump_smt2_node(long jarg1, long jarg2, long jarg3);

  protected final static native void boolector_dump_smt2(long jarg1, long jarg2);

  protected final static native void
      boolector_dump_aiger_ascii(long jarg1, long jarg2, boolean jarg3);

  protected final static native void
      boolector_dump_aiger_binary(long jarg1, long jarg2, boolean jarg3);

  protected final static native String boolector_copyright(long jarg1);

  protected final static native String boolector_version(long jarg1);

  protected final static native String boolector_git_id(long jarg1);

  protected final static native int BTOR_RESULT_SAT_get();

  protected final static native int BTOR_RESULT_UNSAT_get();

  protected final static native int BTOR_RESULT_UNKNOWN_get();

  protected final static native void BtorAbortCallback_abort_fun_set(long jarg1, long jarg2);

  protected final static native long BtorAbortCallback_abort_fun_get(long jarg1);

  protected final static native void BtorAbortCallback_cb_fun_set(long jarg1, long jarg2);

  protected final static native long BtorAbortCallback_cb_fun_get(long jarg1);

  protected final static native long new_BtorAbortCallback();

  protected final static native void delete_BtorAbortCallback(long jarg1);

  protected final static native void btor_abort_callback_set(long jarg1);

  protected final static native long btor_abort_callback_get();

  /**
   * Returns string dump in smt2 format of the entire formula. No guarantee that that string is
   * useful.
   *
   * @param jarg1 btor (environment)
   * @return string dump of btor instance
   */
  protected final static native String boolector_help_dump_smt2(long jarg1);

  /**
   * Returns string dump in smt2 format from btor instance of the chosen node. No guarantee that
   * that string is useful
   *
   * @param jarg1 btor (environment)
   * @param jarg2 node to dump
   * @return string dump of the node
   */
  protected final static native String boolector_help_dump_node_smt2(long jarg1, long jarg2);

  /**
   * Tries to parse the string into boolector.
   *
   * @param jarg1 btor
   * @param jarg2 string to parse
   * @return int representing boolector error/sat codes.
   */
  protected final static native int boolector_help_parse(long jarg1, String jarg2);

  /**
   * Gives back the assignment of the array node entered. Return will be arguments and assignments
   * in 2 string arrays in an array.
   *
   * @param jarg1 btor
   * @param jarg2 array node
   * @return Returns 2Dim Array or Strings. Size [2][x], x beeing the length of the array used.
   *         First String Array will be argument assignment strings. Second String Array will be
   *         value assignment strings.
   */
  protected final static native String[][]
      boolector_array_assignment_helper(long jarg1, long jarg2);

  /**
   * Returns the assignment of the UF node in 2 string arrays in an array length 2.
   *
   * @param jarg1 btor
   * @param jarg2 array node
   * @return Returns 2Dim Array or Strings. Size [2][x], x beeing the length of the uf used. First
   *         String Array will be argument assignment strings. Second String Array will be value
   *         assignment strings.
   */
  protected final static native String[][] boolector_uf_assignment_helper(long jarg1, long jarg2);
}
