/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.core.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.fineract.infrastructure.core.exception.AbstractIdempotentCommandException;
import org.apache.fineract.infrastructure.core.exception.IdempotentCommandProcessSucceedException;
import org.springframework.stereotype.Component;

@Provider
@Component
@Slf4j
public class IdempotentCommandProcessSucceedExceptionMapper implements ExceptionMapper<IdempotentCommandProcessSucceedException> {

    @Override
    public Response toResponse(final IdempotentCommandProcessSucceedException exception) {
        log.debug("Idempotent processing success request: {}", exception.getMessage());
        return Response.status(Status.OK).entity(exception.getResponse())
                .header(AbstractIdempotentCommandException.IDEMPOTENT_CACHE_HEADER, "true").type(MediaType.APPLICATION_JSON).build();
    }
}
