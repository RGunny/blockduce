package com.special.blockduce.transaction.repository;

import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.domain.ETH;
import org.springframework.data.repository.CrudRepository;

public interface EthRepository extends CrudRepository<ETH,Long> {

}
