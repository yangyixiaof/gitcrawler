/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.core.client.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.security.tokens.DelegationToken;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.TokenIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelegationTokenImpl extends PasswordToken implements DelegationToken {
	private static final Logger log = LoggerFactory.getLogger(DelegationTokenImpl.class);

	public static final String SERVICE_NAME = "AccumuloDelegationToken";

	private AuthenticationTokenIdentifier identifier;

	public DelegationTokenImpl(byte[] delegationTokenPassword, AuthenticationTokenIdentifier identifier) {
		checkNotNull(delegationTokenPassword);
		checkNotNull(identifier);
		setPassword(delegationTokenPassword);
		this.identifier = identifier;
	}

	@Override
	public boolean equals(Object obj) {
		// We assume we can cast obj to DelegationToken because the
		// super.equals(obj) check ensures obj is of the same type as this
		return super.equals(obj) && identifier.equals(((DelegationTokenImpl) obj).identifier);
	}

}
