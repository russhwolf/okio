/*
 * Copyright (C) 2020 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okio

import okio.Path.Companion.toPath
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import java.nio.file.Paths
import kotlin.test.Test

class JvmTest {
  @Test
  fun `base directory consistent with java io File`() {
    assertThat(Filesystem.SYSTEM.canonicalize(".".toPath()).toString())
      .isEqualTo(File("").canonicalFile.toString())
  }

  @Test
  fun `java io File to okio Path`() {
    val javaIoFile = File("/foo/bar/baz")
    val okioPath = "/foo/bar/baz".toPath(Path.directorySeparator)
    assertThat(javaIoFile.toOkioPath()).isEqualTo(okioPath)
    assertThat(okioPath.toFile()).isEqualTo(javaIoFile)
  }

  @Test
  fun `nio Path to okio Path`() {
    val nioPath = Paths.get("/foo/bar/baz")
    val okioPath = "/foo/bar/baz".toPath(Path.directorySeparator)
    assertThat(nioPath.toOkioPath()).isEqualTo(okioPath)
    assertThat(okioPath.toNioPath() as Any).isEqualTo(nioPath)
  }
}
