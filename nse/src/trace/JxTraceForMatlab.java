
class JxTraceForMatlab{
  init( String dbtrace );
  select( String sql );
  
  /**
   * Generate node list for matlab.
   */
  nodelist();

  /**
   * Generate edge list for matlab.
   */
  edgelist();
  
  /**
   * Generate network matrix representation for matlab.
   */
  topology();
}